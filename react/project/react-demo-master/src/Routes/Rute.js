import { Card } from "@material-ui/core";
import React, { Fragment, useState, useEffect } from "react";
import { BrowserRouter as Router, Routes , Route, Navigate } from "react-router-dom";
import ManageClients from "../Admin/ManageClients";
import ManageDevices from "../Admin/ManageDevices";
import ManageSensors from "../Admin/ManageSensors";
import NewClientPage from "../Client/NewClientPage";
import Login from "../Login/Login";
import AdminPage from "../Admin/AdminPage";
import ClientPage from "../Client/ClientPage";
import NewDevice from "../Device/NewDevice";
import NewSensor from "../Sensor/NewSensor"
import ChatRoom from "../components/ChatRoom"

const Rute = () => {
    const defaultRoute = window.location.pathname === "/" ? <Navigate  to="/home" /> : undefined;
    return (
        <Router>
            <Routes>
                <Route exact path="/manageClients" element={<ManageClients />} />
                <Route exact path="/manageDevices" element={<ManageDevices />} />
                <Route exact path="/manageSensors" element={<ManageSensors />} />
                <Route exact path="/log-in" element={<Login />} />
                <Route exact path="/home" element={<Login />} />
                <Route exact path="/newClient" element={<NewClientPage />} />
                <Route exact path="/newDevice" element={<NewDevice />} />
                <Route exact path="/newSensor" element={<NewSensor />} />
                <Route exact path="/adminPage" element={<AdminPage />} />
                <Route exact path="/clientPage" element={<ClientPage />} />
                <Route exact path="/ChatRoom" element={<ChatRoom />} />
            </Routes>
            {/*{defaultRoute}*/}
        </Router>
    );
};

export default Rute;
