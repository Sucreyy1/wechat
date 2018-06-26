config_host=172.32.3.210
config_profile=test
eureka_host=172.32.3.210
script_path=$(cd `dirname $0`;pwd)
pid=0

list=(
	1,micro-gateway-client-service,1
	2,micro-web-backend-service,1
	3,micro-zuul-proxy,1
	4,micro-zuul-web-service,1
	5,micro-config-server,0
	6,micro-eureka-server,0
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

startUp(){
	getPID $1
	if [ $pid -eq 0 ] ; then
		echo "$1 already started PID=[$pid]...please shutdown first..."
		echo "================================"
	else
		echo "starting [$1]"
		nohup java $2 -jar ${script_path}/../$1'-0.0.1-SNAPSHOT'.jar $3 >/dev/null 2>&1 &
		getPID $1
		if [ $pid -ne 0 ] ; then
			echo "$1 start success PID=[$pid]"
			echo "==========================="
		else
			echo "$1 start failed PID=[$pid]"
			echo "==========================="
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
		need_opt=`echo $apps | cut -d , -f 3`
		JVM_OPTS="-Xms2048m -Xmx2048m -Xss256k -XX:PermSize=64m -XX:MaxPermSize=128m -Xmn1500m -XX:SurvivorRatio=4 -XX:+UseParallelGC -XX:ParallelGCThreads=8 -XX:+UseParallelOldGC -XX:MaxGCPauseMillis=100 -XX:+UseAdaptiveSizePolicy -XX:+PrintGCDetails -xloggc:${app_name}-gclog.log"
		JAVA_OPTS="--config.profile=$config_profile --eureka.host=$eureka_host --config.host=$config_host"
		if [ $param -eq 0 ] ; then
			startUp "$app_name" "$JVM_OPTS" "$JAVA_OPTS"
		elseif [ "$param" -eq "$app_index" ] ; then
			if[ $need_opt -eq 1 ] ; then
				startUp "$app_name" "$JVM_OPTS" "$JAVA_OPTS"
			else
				startUp "$app_name"
			fi
		fi
	done
done
			