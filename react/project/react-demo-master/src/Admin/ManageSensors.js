import React from "react";
import {
    Container,
    Box,
    Typography,
    Avatar,
    Grid,
    Card,
    CardContent,
} from "@material-ui/core";
import { Edit, Person } from "@material-ui/icons";
import {
    ArrowDownward,
    Check,
    ChevronLeft,
    ChevronRight,
    Clear,
    DeleteOutline,
    FirstPage,
    FilterList,
    LastPage,
    SaveAlt,
    Remove,
    Search,
    ViewColumn,
    AddBox,
} from "@material-ui/icons";
//import './AdminOperations.css';
import MaterialTable from "material-table";
import axiosInstance from "../axios";

import { forwardRef } from "react";
import { Button } from "@material-ui/core";
import Layout from "../Layout";
import { Link } from "react-router-dom";

const tableIcons = {
    Add: forwardRef(<AddBox />),
    Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
    Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
    Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
    DetailPanel: forwardRef((props, ref) => (
        <ChevronRight {...props} ref={ref} />
    )),
    Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
    Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
    Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
    FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
    LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
    NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
    PreviousPage: forwardRef((props, ref) => (
        <ChevronLeft {...props} ref={ref} />
    )),
    ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
    Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
    SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
    ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
    ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />),
};

class ManageSensors extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            idDevice: null,
            infoDevices: [],
            infoSensor: {
                id: 0,
                sensorDescription: "",
                maxValue: 0,
            },
        };
        this.displayDevice.bind(this);
    }

    //setam totul din state (preluam din backend)
    componentDidMount() {
        this.displayDevice();
    }

    displayDevice = () => {
        const data = localStorage.getItem("USER");
        this.setState({ role: data });
        const data1 = localStorage.getItem("USER_ID");
        this.setState({ idClient: data1 });

        if (this.state.role === "CLIENT") {
            this.props.history.push("/clientmenu");
        } else {
            axiosInstance
                .get(`/sensor`)
                .then((res) => {
                    const val = res.data;
                    console.log(val);
                    this.setState({
                        infoDevices: val,
                    });
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    };

    render() {
        const data = localStorage.getItem("USER");
        if (data === "ADMIN") {
            return (
                <Layout>
                    <Box className="profileContent">
                        <Container maxWidth="xl">
                            <Box p={5}>
                                <Typography variant="h3">Admin Profile</Typography>
                            </Box>

                            <Grid container spacing={1}>
                                <Grid item sm={12} md={12} spacing={3}>
                                    <Card className="cardHeight">
                                        <CardContent>
                                            <Avatar>
                                                <Person color="secondary"></Person>
                                            </Avatar>
                                            <Typography color="secondary" component="p" variant="h4">
                                                Sensors
                                            </Typography>

                                            <MaterialTable
                                                cellEditable={{
                                                    cellStyle: {},
                                                    onCellEditApproved: (
                                                        newValue,
                                                        oldValue,
                                                        rowData,
                                                        columnDef
                                                    ) => {
                                                        return new Promise((resolve, reject) => {

                                                            let sensorInfo = {
                                                                id: rowData.id,
                                                                deviceId: rowData.device.id,
                                                                sensorDescription: rowData.sensorDescription,
                                                                maxValue: rowData.maxValue,
                                                            };
                                                            let columnName = columnDef.field === 'device.id' ? 'deviceId' : columnDef.field;
                                                            sensorInfo = {
                                                                ...sensorInfo,
                                                                [columnName]: newValue,
                                                            };

                                                            setTimeout(
                                                                axiosInstance
                                                                    .put("/sensor", sensorInfo)
                                                                    .then((res) => {
                                                                        const dto = res.data;
                                                                        console.log("dto", dto)
                                                                    })
                                                                    .catch((error) => {
                                                                        console.log(error);
                                                                    }),
                                                                axiosInstance
                                                                    .get(`/sensor`)
                                                                    .then((res) => {
                                                                        const val = res.data;
                                                                        console.log(val);
                                                                        this.setState({
                                                                            infoDevices: val,
                                                                        });
                                                                    })
                                                                    .catch((error) => {
                                                                        console.log(error);
                                                                    }),

                                                                resolve,
                                                                4000
                                                            );
                                                        });
                                                    },
                                                }}
                                                icons={tableIcons}
                                                title="Edit sensors"
                                                columns={[
                                                    {
                                                        title: "ID",
                                                        field: "id",
                                                    },
                                                    {
                                                        title: "Assigned to Device",
                                                        field: "device.id",
                                                    },
                                                    {
                                                        title: "Device",
                                                        field: "device.description",
                                                    },
                                                    {
                                                        title: "Description",
                                                        field: "sensorDescription",
                                                    },
                                                    {
                                                        title: "Max Value",
                                                        field: "maxValue",
                                                    },
                                                ]}
                                                data={this.state.infoDevices.map((item) =>
                                                    Object.assign({}, item)
                                                )}
                                                editable={{
                                                    onRowDelete: (oldData) =>
                                                        new Promise((resolve, reject) => {
                                                            setTimeout(() => {
                                                                let clone = [];
                                                                Object.assign(clone, this.state.infoDevices);
                                                                const id = oldData.id;
                                                                axiosInstance
                                                                    .put(`/sensor/delete/${id}`)
                                                                    .then((res) => {
                                                                        clone.splice(id, 0);

                                                                        this.setState({ infoDevices: res.data });
                                                                    })
                                                                    .catch((error) => {
                                                                        console.log(error);
                                                                    });
                                                                resolve();
                                                            }, 1000);
                                                        }),
                                                }}
                                            />
                                        </CardContent>
                                    </Card>
                                </Grid>
                            </Grid>
                        </Container>
                    </Box>

                    <Link to="/newSensor">
                        <Button style={{ float: "right" }}>Add new sensor</Button>
                    </Link>
                </Layout>
            );
        } else {
            return (
                <Layout>
                    <Card>
                        <img src="https://i.pinimg.com/originals/96/3f/32/963f328b1767c8df34941d929f8f0a89.jpg" />
                    </Card>
                </Layout>
            );
        }
    }
}

export default ManageSensors;
