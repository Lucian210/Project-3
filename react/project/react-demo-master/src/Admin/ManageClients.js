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
    ArrowDownward,
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

class ManageClients extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            idClient: null,
            role: "",
            infoClients: [],
        };
        this.displayClient.bind(this);
    }

    //setam totul din state (preluam din backend)
    componentDidMount() {
        this.displayClient();
    }

    displayClient = () => {
        const data = localStorage.getItem("USER");
        this.setState({ role: data });
        const data1 = localStorage.getItem("USER_ID");
        this.setState({ idClient: data1 });

        if (this.state.role === "CLIENT") {
            this.props.history.push("/clientmenu");
        } else {
            axiosInstance
                .get(`/client`)
                .then((res) => {
                    const val = res.data;
                    console.log(val);
                    this.setState({
                        infoClients: val,
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
                                                Clients
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
                                                            let clientInfo = {
                                                                id: rowData.id,
                                                                name: rowData.name,
                                                                address: rowData.address,
                                                                birthDate: rowData.birthDate,
                                                            };

                                                            clientInfo = {
                                                                ...clientInfo,
                                                                [columnDef.field]: newValue,
                                                            };

                                                            setTimeout(
                                                                axiosInstance
                                                                    .put("/client", clientInfo)
                                                                    .then((res) => {
                                                                        const dto = res.data;
                                                                        console.log("dto", dto);
                                                                    })
                                                                    .catch((error) => {
                                                                        console.log(error);
                                                                    }),
                                                                axiosInstance
                                                                    .get(`/client`)
                                                                    .then((res) => {
                                                                        const val = res.data;
                                                                        console.log(val);
                                                                        this.setState({
                                                                            infoClients: val,
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
                                                title="Edit clients"
                                                columns={[
                                                    {
                                                        title: "ID",
                                                        field: "id",
                                                    },
                                                    {
                                                        title: "Nume",
                                                        field: "name",
                                                    },
                                                    {
                                                        title: "Address",
                                                        field: "address",
                                                    },
                                                    {
                                                        title: "Birthdate",
                                                        field: "birthDate",
                                                    },
                                                ]}
                                                data={this.state.infoClients.map((item) =>
                                                    Object.assign({}, item)
                                                )}
                                                editable={{
                                                    onRowDelete: (oldData) =>
                                                        new Promise((resolve, reject) => {
                                                            setTimeout(() => {
                                                                let clone = [];
                                                                Object.assign(clone, this.state.infoClients);
                                                                const id = oldData.id;
                                                                axiosInstance
                                                                    .put(`/client/delete/${id}`)
                                                                    .then((res) => {
                                                                        clone.splice(id, 0);

                                                                        this.setState({ infoClients: res.data });
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

                    <Link to="/newClient">
                        <Button style={{ float: "right" }}>Add client</Button>
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

export default ManageClients;
