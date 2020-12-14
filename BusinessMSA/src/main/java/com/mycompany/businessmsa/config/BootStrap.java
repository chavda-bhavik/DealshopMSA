package com.mycompany.businessmsa.config;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import org.eclipse.microprofile.auth.LoginConfig;

@SuppressWarnings({"EmptyClass", "SuppressionAnnotation"})
@ApplicationPath("rest")
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"Admin","Business"})
public class BootStrap extends javax.ws.rs.core.Application {
}
