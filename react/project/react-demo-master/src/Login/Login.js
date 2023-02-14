import React, { useState } from "react";
import {
    CssBaseline,
    Checkbox,
    Grid,
    Link,
    Paper,
    Box,
    Avatar,
    Typography,
    TextField,
    Button,
} from "@material-ui/core";
//import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import {
    createTheme,
    ThemeProvider,
    FormControlLabel,
} from "@material-ui/core";
import axiosInstance from "../axios";
import { useNavigate, Navigate } from "react-router-dom";

const theme = createTheme();

export default function SignInSide() {
    const navigate = useNavigate();
    const [redirectToAdmin, setRedirectToAdmin] = useState(false);
    const [redirectToClient, setRedirectToClient] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        let credentials = {
            username: data.get("email"),
            password: data.get("password"),
        };

        axiosInstance
            .post("/login", credentials)
            .then((res) => {
                localStorage.setItem("USER", res.data.role);
                localStorage.setItem("USER_ID", res.data.id);

                if (res.data.role === "CLIENT") {
                    navigate('/clientPage');
                    setRedirectToClient(true);
                } else {
                    navigate('/manageClients');
                    setRedirectToAdmin(true);
                }
            })
            .catch((err) => {
                console.log(err);
                alert("wrong credentials!");
            });
    };

    return (
        <ThemeProvider theme={theme}>
            <Grid container component="main" sx={{ height: "100vh" }}>
                <CssBaseline />
                <Grid
                    item
                    xs={false}
                    sm={4}
                    md={7}
                    sx={{
                        backgroundImage:
                            "url(https://source.unsplash.com/JvQ0Q5IkeMM/640x960)",
                        backgroundRepeat: "no-repeat",
                        backgroundColor: (t) =>
                            t.palette.mode === "light"
                                ? t.palette.grey[50]
                                : t.palette.grey[900],
                        backgroundSize: "cover",
                        backgroundPosition: "center",
                    }}
                />
                <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
                    <Box
                        sx={{
                            my: 8,
                            mx: 4,
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "center",
                        }}
                    >
                        <Typography component="h1" variant="h5">
                            Sign in
                        </Typography>
                        <Box
                            component="form"
                            noValidate
                            onSubmit={handleSubmit}
                            sx={{ mt: 1 }}
                        >
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="email"
                                label="Email Address"
                                name="email"
                                autoComplete="email"
                                autoFocus
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                autoComplete="current-password"
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                sx={{ mt: 3, mb: 2 }}
                            >
                                Sign In
                            </Button>
                        </Box>
                    </Box>
                </Grid>
            </Grid>

            {redirectToAdmin && <Navigate to = "/manageClients"/>}
            {redirectToClient && <Navigate to = "/client"/>}
        </ThemeProvider>
    );
}
