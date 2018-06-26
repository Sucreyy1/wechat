#应用程序pid
pid=0

list=(
	1,micro-gateway-client-service
	2,micro-web-backend-service
	3,micro-zuul-proxy
	4,micro-zuul-web-service
	5,micro-config-server
	6,micro-eureka-server
)

#获取应用pid
getPID(){
	javaps=`jps -l | grep $1`
	if [ -n "$javaps" ] ; then
		pid=`echo $javaps | awk '{print $1}'`
	else
		pid=0
	fi
}

#停止应用
shutdown(){
	getPID $1
	if [ $pid -ne 0 ] ; then
		echo "now shutting down $1"
		kill -9 $pid
		getPID $1
		if [ $pid -eq 0 ] ; then
			echo "[success]:$1 shut down success..."
			echo "==============================="
		else
			echo "[fail]:shut down $1 failed , please check 'defunct' progress..."
			echo "==============================="
		fi
	else
		echo "[warn]:$1 is not running..."
	fi	
}

echo "请选择要停止的应用:
		0,全部停止
		1,micro-gateway-client-service
        2,micro-web-backend-service
        3,micro-zuul-proxy
        4,micro-zuul-web-service
        5,micro-config-server
        6,micro-eureka-server
		(多个应用使用空格分割)"
read -p "请选择: " input_params

if [ ! -n "$input_params" ] ; then
	echo "参数不能为空..."
	exit 0
fi

for param in $input_params
do
	for apps in ${list[@]}
	do
		app_index=`echo $apps | cut -d , -f 1`
		app_name=`echo $apps | cut -d , -f 2`
		if [ $param -eq 0 ] ; then
			shutdown "$app_name"
		elif [ "$param" -eq "$app_index" ] ; then
			shutdown "$app_name"
		fi
	done
done