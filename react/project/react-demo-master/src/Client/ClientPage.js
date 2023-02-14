import React, { useState, useEffect } from "react";
import ClientLayout from "../ClientLayout";
import axiosInstance from "../axios";
import { Typography } from "@material-ui/core";
import {XAxis, LineChart, Tooltip, CartesianGrid, Line, YAxis} from "recharts";


function ClientPage() {
    const [infoClient, setInfoClient] = useState({});
    const [infoDevices, setInfoDevices] = useState([]);
    const [dataSensors, setDataSensors] = useState([])

    useEffect(() => {
        axiosInstance
            .get(`/client/${localStorage.getItem("USER_ID")}`)
            .then((res) => {
                const val = res.data;
                setInfoClient(val);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    useEffect( () => {
        axiosInstance
            .get(`/device/clientDevice/${localStorage.getItem("USER_ID")}`)
            .then((res) => {
                const val = res.data;
                setInfoDevices(val);
                console.log(val);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    useEffect( () => {
        axiosInstance
            .get(`/sensor/clientSensorInfo/${localStorage.getItem("USER_ID")}`)
            .then((res) => {
                const val = res.data;
                setDataSensors(val);
                console.log("Data sensors", val);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);


    useEffect(() => {
        console.log(infoClient);
    }, [infoClient]);

    const sumOfArray = () => {
        let sum = 0;
        infoDevices.map(currentDevice => sum += currentDevice.maxEnergyCons);
        return sum;
    };

    return (
        <ClientLayout>
            <Typography variant="h3">Hello, {infoClient.name}!</Typography>
            <h1>Your devices: </h1>
            {infoDevices.map((currentDevice, index) => (
                <h3 key={index}>
                    {currentDevice.description} : (Max energy consumption ={" "}
                    {currentDevice.maxEnergyCons}
                    {})
                </h3>
            ))}
            <h1>Total energy consumption:  {sumOfArray()}</h1>
            <LineChart
                width={400}
                height={400}
                data={dataSensors}
                margin={{ top: 5, right: 20, left: 10, bottom: 5 }}
            >
                <XAxis dataKey="timeStamp" />
                <YAxis/>
                <Tooltip />
                <CartesianGrid stroke="#f5f5f5" />
                <Line type="monotone" dataKey="cons" stroke="#ff7300" yAxisId={0} />
            </LineChart>
        </ClientLayout>
    );
}

export default ClientPage;
