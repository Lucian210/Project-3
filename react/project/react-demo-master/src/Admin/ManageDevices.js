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
    AddBox
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

class ManageDevices extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            idDevice: null,
            infoDevices: [],
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
                .get(`/device`)
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
                                                Devices
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

                                                            let deviceInfo = {
                                                                id: rowData.id,
                                                                clientId: rowData.client.id,
                                                                description: rowData.description,
                                                                maxEnergyCons: rowData.maxEnergyCons,
                                                                avgEnergyCons: rowData.avgEnergyCons,
                                                            };
                                                            let columnName = columnDef.field === 'client.id' ? 'clientId' : columnDef.field;
                                                            deviceInfo = {
                                                                ...deviceInfo,
                                                                [columnName]: newValue,
                                                            };

                                                            setTimeout(
                                                                axiosInstance
                                                                    .put("/device", deviceInfo)
                                                                    .then((res) => {
                                                                        const dto = res.data;
                                                                        console.log("dto", dto)
                                                                    })
                                                                    .catch((error) => {
                                                                        console.log(error);
                                                                    }),
                                                                axiosInstance
                                                                    .get(`/device`)
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
                                                title="Edit devices"
                                                columns={[
                                                    {
                                                        title: "ID",
                                                        field: "id",
                                                    },
                                                    {
                                                        title: "Assigned to client",
                                                        field: "client.id",
                                                    },
                                                    {
                                                        title: "Client",
                                                        field: "client.name",
                                                    },
                                                    {
                                                        title: "Description",
                                                        field: "description",
                                                    },
                                                    {
                                                        title: "Max Energy Consumption",
                                                        field: "maxEnergyCons",
                                                    },
                                                    {
                                                        title: "Avg Energy Consumption",
                                                        field: "avgEnergyCons",
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
                                                                    .put(`/device/delete/${id}`)
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

                    <Link to="/newDevice">
                        <Button style={{ float: "right" }}>Add new device</Button>
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

export default ManageDevices;
