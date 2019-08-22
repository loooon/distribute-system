#!/bin/sh
export SERVER_SERVICE=server-service/target/server-service-0.0.1-SNAPSHOT.jar
export TURBINE_SERVICE=turbine-service/target/turbine-service-0.0.1-SNAPSHOT.jar
export ZUUL_SERVICE=zuul-service/target/zuul-service-0.0.1-SNAPSHOT.jar
export PRODUCT_SERVICE=product-service/target/product-service-0.0.1-SNAPSHOT.jar
export ORDER_SERVICE=order-service/target/order-service-0.0.1-SNAPSHOT.jar
export GROSS_SETTLEMENT_SERVICE=gross-settlement-service/target/gross-settmement-service-0.0.1-SNAPSHOT.jar
export CONFIG_SERVICE=config-service/target/config-service-0.0.1-SNAPSHOT.jar

export SERVER_SERVICE_PORT=8761
export TURBINE_SERVICE_PORT=12086
export ZUUL_SERVICE_PORT=9000
export PRODUCT_SERVICE_PORT=9100
export ORDER_SERVICE_PORT=9200
export GROSS_SETTLEMENT_SERVICE_PORT=9300
export CONFIG_SERVICE_PORT=8888

case "$1" in

start)
        echo "-------- 开始打包 ----------------"
        mvn clean package
        echo "-------- 完成打包 ----------------"
        ## 启动SERVER_SERVICE
        echo "--------启动server-service 开始启动--------------"
        nohup java -jar $SERVER_SERVICE >/dev/null 2>&1 &
        SERVER_SERVICE_PID=`lsof -i:$SERVER_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$SERVER_SERVICE_PID" ]
            do
              SERVER_SERVICE_PID=`lsof -i:$SERVER_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
            done
        echo "SERVER_SERVICE pid is $SERVER_SERVICE_PID"
        echo "--------SERVER_SERVICE 启动成功--------------"

        ## 启动TURBINE_SERVICE
        echo "--------开始启动TURBINE_SERVICE---------------"
        nohup java -jar $TURBINE_SERVICE >/dev/null 2>&1 &
        TURBINE_SERVICE_PID=`lsof -i:$TURBINE_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$TURBINE_SERVICE_PID" ]
            do
              TURBINE_SERVICE_PID=`lsof -i:$TURBINE_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
            done
        echo "TURBINE_SERVICE pid is $TURBINE_SERVICE_PID"
        echo "---------TURBINE_SERVICE 启动成功-----------"


        ## 启动ZUUL_SERVICE
        echo "--------开始启动ZUUL_SERVICE---------------"
        nohup java -jar $ZUUL_SERVICE >/dev/null 2>&1 &
        ZUUL_SERVICE_PID=`lsof -i:$ZUUL_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$ZUUL_SERVICE_PID" ]
            do
              ZUUL_SERVICE_PID=`lsof -i:$ZUUL_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
            done
        echo "ZUUL_SERVICE pid is $ZUUL_SERVICE_PID"
        echo "---------ZUUL_SERVICE 启动成功-----------"


        ## 启动CONFIG_SERVICE
        echo "--------开始启动CONFIG_SERVICE---------------"
        nohup java -jar $CONFIG_SERVICE >/dev/null 2>&1 &
        CONFIG_SERVICE_PID=`lsof -i:$CONFIG_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$CONFIG_SERVICE_PID" ]
            do
              CONFIG_SERVICE_PID=`lsof -i:$CONFIG_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
            done
        echo "CONFIG_SERVICE pid is $CONFIG_SERVICE_PID"
        echo "---------CONFIG_SERVICE 启动成功-----------"


        ## 启动PRODUCT_SERVICE
        echo "--------开始启动PRODUCT_SERVICE---------------"
        nohup java -jar $PRODUCT_SERVICE >/dev/null 2>&1 &
        PRODUCT_SERVICE_PID=`lsof -i:$PRODUCT_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$PRODUCT_SERVICE_PID" ]
            do
              PRODUCT_SERVICE_PID=`lsof -i:$PRODUCT_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
            done
        echo "PRODUCT_SERVICE pid is $PRODUCT_SERVICE_PID"
        echo "---------PRODUCT_SERVICE 启动成功-----------"

        ## 启动ORDER_SERVICE
        echo "--------开始启动ORDER_SERVICE---------------"
        nohup java -jar $ORDER_SERVICE >/dev/null 2>&1 &
        ORDER_SERVICE_PID=`lsof -i:$ORDER_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$ORDER_SERVICE_PID" ]
            do
              ORDER_SERVICE_PID=`lsof -i:$ORDER_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
            done
        echo "ORDER_SERVICE pid is $ORDER_SERVICE_PID"
        echo "---------ORDER_SERVICE 启动成功-----------"

        ## 启动GROSS_SETTLEMENT_SERVICE
        echo "--------开始启动GROSS_SETTLEMENT_SERVICE---------------"
        nohup java -jar $GROSS_SETTLEMENT_SERVICE >/dev/null 2>&1 &
        GROSS_SETTLEMENT_SERVICE_PID=`lsof -i:$GROSS_SETTLEMENT_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$GROSS_SETTLEMENT_SERVICE_PID" ]
            do
              GROSS_SETTLEMENT_SERVICE_PID=`lsof -i:$GROSS_SETTLEMENT_SERVICE_PORT|grep "LISTEN"|awk '{print $2}'`
            done
        echo "GROSS_SETTLEMENT_SERVICE pid is $GROSS_SETTLEMENT_SERVICE_PID"
        echo "---------GROSS_SETTLEMENT_SERVICE 启动成功-----------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $SERVER_SERVICE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===SERVER_SERVICE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "SERVER_SERVICE killed success"
        fi
		P_ID=`ps -ef | grep -w $TURBINE_SERVICE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===TURBINE_SERVICE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "TURBINE_SERVICE killed success"
        fi
        P_ID=`ps -ef | grep -w $ZUUL_SERVICE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===ZUUL_SERVICE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "ZUUL_SERVICE killed success"
        fi


        P_ID=`ps -ef | grep -w $CONFIG_SERVICE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===CONFIG_SERVICE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "CONFIG_SERVICE killed success"
        fi


        P_ID=`ps -ef | grep -w $PRODUCT_SERVICE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===PRODUCT_SERVICE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "PRODUCT_SERVICE killed success"
        fi

        P_ID=`ps -ef | grep -w $ORDER_SERVICE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===ORDER_SERVICE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "ORDER_SERVICE killed success"
        fi

        P_ID=`ps -ef | grep -w $GROSS_SETTLEMENT_SERVICE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===GROSS_SETTLEMENT_SERVICE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "GROSS_SETTLEMENT_SERVICE killed success"
        fi


        echo "===stop success==="
        ;;

restart)
        $0 stop
        sleep 2
        $0 start
        echo "===restart success==="
        ;;
esac
exit 0