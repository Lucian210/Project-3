import React, { useState } from "react";
import { createTheme, ThemeProvider } from "@material-ui/core";
import {
  Container,
  Box,
  Typography,
  Avatar,
  Grid,
  Button,
  TextField,
  CssBaseline,
  
} from "@material-ui/core";
//mport LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import axiosInstance from "../axios";
import { useNavigate } from "react-router-dom";
import Layout from "../Layout";

const theme = createTheme();

export default function NewClientPage() {
  const history = useNavigate();
  const [clientInfo, setClientInfo] = useState({
    firstName: "",
    lastName: "",
    username: "",
    password: "",
    address: "",
    birthDate: "",
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log(clientInfo);

    axiosInstance
      .post("/newUser", clientInfo)
      .then((res) => {
        const val = res.data;

        history.push("/manageClients");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleOnChange = (event) => {
    const { value, name } = event.target;
    setClientInfo({ ...clientInfo, [name]: value });
  };

  return (
    <ThemeProvider theme={theme}>
      <Layout>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            {/*<Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
              <LockOutlinedIcon />
          </Avatar>*/}
            <Typography component="h1" variant="h5">
              Add new client
            </Typography>
            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit}
              sx={{ mt: 3 }}
            >
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    onChange={handleOnChange}
                    autoComplete="fname"
                    name="firstName"
                    required
                    fullWidth
                    id="firstName"
                    label="First Name"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    onChange={handleOnChange}
                    required
                    fullWidth
                    id="lastName"
                    label="Last Name"
                    name="lastName"
                    autoComplete="lname"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    onChange={handleOnChange}
                    required
                    fullWidth
                    name="username"
                    label="Username"
                    id="username"
                    autoComplete="password"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    onChange={handleOnChange}
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="new-password"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    onChange={handleOnChange}
                    required
                    fullWidth
                    id="address"
                    label="Address"
                    name="address"
                    autoComplete="address"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    onChange={handleOnChange}
                    required
                    fullWidth
                    id="birthDate"
                    label="Birth Date (format DD/MM/YYY)"
                    name="birthDate"
                    autoComplete="birthDate"
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Add new client
              </Button>
              <Grid container justifyContent="flex-end">
                <Grid item></Grid>
              </Grid>
            </Box>
          </Box>
        </Container>
      </Layout>
    </ThemeProvider>
  );
}
