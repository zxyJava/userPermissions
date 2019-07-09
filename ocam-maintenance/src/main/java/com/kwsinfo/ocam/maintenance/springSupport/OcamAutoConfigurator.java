package com.kwsinfo.ocam.maintenance.springSupport;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.kwsinfo.ocam.maintenance.core.IAccount;
import com.kwsinfo.ocam.maintenance.core.IMenu;
import com.kwsinfo.ocam.maintenance.core.IRole;
import com.kwsinfo.ocam.maintenance.core.IUser;
import com.kwsinfo.ocam.maintenance.core.dao.*;
import com.kwsinfo.ocam.maintenance.core.service.IAccountUserService;
import com.kwsinfo.ocam.maintenance.core.service.IMenuService;
import com.kwsinfo.ocam.maintenance.core.service.IRoleService;
import com.kwsinfo.ocam.maintenance.ocamDefault.dao.*;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import com.kwsinfo.ocam.maintenance.ocamDefault.repository.*;
import com.kwsinfo.ocam.maintenance.ocamDefault.service.AccountUserServiceImpl;
import com.kwsinfo.ocam.maintenance.ocamDefault.service.MenuServiceImpl;
import com.kwsinfo.ocam.maintenance.ocamDefault.service.RoleServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.sql.DataSource;


@Configuration
@EnableConfigurationProperties(KwsSecurityConfigProperties.class)
@ConditionalOnProperty(prefix = "kws.security.config", value = "enabled", matchIfMissing = true)
// kws.security.config.enabled=false
public class OcamAutoConfigurator {

    @Bean
    @ConditionalOnMissingBean(IAccountUserService.class)
    public <A extends IAccount<U>, U extends IUser> IAccountUserService<A, U> accountUserService(IAccountPersistence<A> accountPersistence, IUserPersistence<U> userPersistence, IRolePersistence<Role> roleIRolePersistence, IMenuPersistence<Menu> menuIMenuPersistence) {
        final AccountUserServiceImpl<A, U> auAccountUserService = new AccountUserServiceImpl<>();
        auAccountUserService.setAccountPersistence(accountPersistence);
        auAccountUserService.setUserPersistence(userPersistence);
        auAccountUserService.setRolePersistence(roleIRolePersistence);
        auAccountUserService.setMenuPersistence(menuIMenuPersistence);
        return auAccountUserService;
    }

    @Bean
    @ConditionalOnMissingBean(IMenuService.class)
    public <M extends IMenu> IMenuService<M> menuService(IMenuPersistence<M> menuPersistence, IPermissionPersistence<Permission> permissionIPermissionPersistence) {
        final MenuServiceImpl<M> auMenuService = new MenuServiceImpl<>();
        auMenuService.setMenuPersistence(menuPersistence);
        return auMenuService;
    }

    @Bean
    @ConditionalOnMissingBean(IRoleService.class)
    public <R extends IRole> IRoleService<R> roleService(IRolePersistence<R> rolePersistence, IMenuPersistence<Menu> menuPersistence) {
        final RoleServiceImpl<R> auRoleService = new RoleServiceImpl<>();
        auRoleService.setRolePersistence(rolePersistence);
        auRoleService.setMenuPersistence(menuPersistence);
        return auRoleService;
    }

    /*
     * 通过配置可以手动关闭
     *  如果用户希望用IAccountUserService，但是dao自己实现，可以把userDefaultJpa设成false,然后自己实现数据库操作
     */
    @ConditionalOnProperty(prefix = "kws.security.config.dao", value = "userDefaultJpa", havingValue = "true", matchIfMissing = true)
    @EnableJpaRepositories(basePackages = "com.kwsinfo.ocam.maintenance.ocamDefault.repository")
    @EntityScan("com.kwsinfo.ocam.maintenance.ocamDefault.model")
    @ConditionalOnClass(DataSource.class)
    public static class JpaDao {

        @Bean
        @ConditionalOnMissingBean(IAccountPersistence.class)
        public IAccountPersistence accountPersistence(AccountRepository repository) {
            final AccountPersistenceImpl accountPersistence = new AccountPersistenceImpl();
            accountPersistence.setAccountRepository(repository);
            return accountPersistence;
        }

        @Bean
        @ConditionalOnMissingBean(IMenuPersistence.class)
        public IMenuPersistence menuPersistence(MenuRepository repository, PermissionRepository permissionRepository) {
            final MenuPersistenceImpl menuPersistence = new MenuPersistenceImpl();
            menuPersistence.setMenuRepository(repository);
            return menuPersistence;
        }

        @Bean
        @ConditionalOnMissingBean(IRolePersistence.class)
        public IRolePersistence rolePersistence(RoleRepository repository) {
            final RolePersistenceImpl rolePersistence = new RolePersistenceImpl();
            rolePersistence.setRoleRepository(repository);
            return rolePersistence;
        }

        @Bean
        @ConditionalOnMissingBean(IPermissionPersistence.class)
        public IPermissionPersistence permissionPersistence(PermissionRepository permissionRepository) {
            final PermissionPersistence permissionPersistence = new PermissionPersistence();
            permissionPersistence.setPermissionRepository(permissionRepository);
            return permissionPersistence;
        }

        @Bean
        @ConditionalOnMissingBean(IUserPersistence.class)
        public IUserPersistence userPersistence(UserRepository userRepository) {
            final UserPersistence userPersistence = new UserPersistence();
            userPersistence.setUserRepository(userRepository);
            return userPersistence;
        }

    }

    @ConditionalOnProperty(prefix = "kws.security.config.controller", value = "enabled", havingValue = "true", matchIfMissing = true)
    @ComponentScan(basePackages = "com.kwsinfo.ocam.maintenance.ocamDefault.controller")
    @Configuration
    public static class DefaultController {

    }

    @SuppressWarnings("unchecked")
    @Bean
    public Jackson2ObjectMapperBuilder configureObjectMapper() {
        return new Jackson2ObjectMapperBuilder().modulesToInstall(Hibernate5Module.class);
    }

}
