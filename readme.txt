1) si desea modificar los nombres app-domain por {app}-domain
			        app-webapp por {app}-webapp
2) en el pom.xml modificar lo sgte:
	 <modelVersion>4.0.0</modelVersion>
	 <groupId>com.app</groupId>
    acá poner algo como :
	 <modelVersion>4.0.0</modelVersion>
	 <groupId>com.{app}</groupId>
	
3) modificar estas entradas: para q apunten a los nuevos directorios q contengan eso.
    <modules>
        <module>app-domain</module>
        <module>app-webapp</module>
    </modules>


