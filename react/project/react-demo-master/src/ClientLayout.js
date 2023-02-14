import { AccountBox, AddAlert, ExitToApp, Home } from "@material-ui/icons";
import {
    Avatar,
    Drawer,
    Grid,
    List,
    ListItem,
    ListItemText,
    Divider
} from "@material-ui/core";
import React from "react";
import { Link } from "react-router-dom";

const ClientLayout = ({children}) => {
    return (
        <Grid container>
            <Grid item xs={2} sx = {{backgroundColor: 'rgba(0,0,0,0.5)', color: '#fff', height: '100vh'}}>

                <List>

                    <ListItem button component={Link} to="/clientPage">
                        <Avatar>
                            <Home color="secondary" />
                        </Avatar>
                        <ListItemText disableTypography className="customFont">
                            {" "}
                            Devices overview
                        </ListItemText>
                    </ListItem>

                    <ListItem button component={Link} to="/ChatRoom">
                                            <Avatar>
                                                <Home color="secondary" />
                                            </Avatar>
                                            <ListItemText disableTypography className="customFont">
                                                {" "}
                                                Chat Room
                                            </ListItemText>
                                        </ListItem>

                    <Divider />

                    <ListItem button component={Link} to="/log-in" className="bottom" onClick = {() => {
                        localStorage.removeItem("USER");
                        localStorage.removeItem("USER_ID");
                    }}>
                        <Avatar>
                            <ExitToApp color="secondary" />
                        </Avatar>
                        <ListItemText disableTypography className="customFont">
                            {" "}
                            Log out
                        </ListItemText>
                    </ListItem>
                </List>

            </Grid>
            <Grid item xs = {10}>
                {children}
            </Grid>
        </Grid>
    );
};

export default ClientLayout;
