antes de ejecutar remusystem hay que crear la variable de ambiente en el sistem operativo: "remusystem_report".  Esta variable debe contener la ruta completa, desde la raíz a la carpeta que contiene los reportes (*.jasper).  Esta carpeta es remu_report y hay que copiarla al disco.

Por ejemplo, en linux hay que poner en el archivo .bashrc la siguiente línea:
export remusystem_report="/home/remu-user/remu_report/"

es importante poner / al final.
