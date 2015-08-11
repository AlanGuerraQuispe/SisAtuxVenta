update servidor_tm set ip_servidor='192.168.1.100' where PK_SERVIDOR=4;

update servidor_tm set ip_servidor='mail.agile-works.com', login_servidor='asistencia@agile-works.com', password_servidor='awpassword', param_adic_servidor='sender=admin-sider@agile-works.com' where PK_SERVIDOR=3;

commit;

exit