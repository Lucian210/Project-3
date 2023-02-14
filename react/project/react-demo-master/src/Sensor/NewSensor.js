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

export default function NewSensor() {
  const history = useNavigate();
  const [sensorInfo, setSensorInfo] = useState({
    deviceId: 0,
    description: "",
    maxValue: ""
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log(sensorInfo);

    axiosInstance
      .post("/sensor/newSensor", sensorInfo)
      .then((res) => {
        const val = res.data;

        history.push("/manageSensors");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleOnChange = (event) => {
    const { value, name } = event.target;
    setSensorInfo({ ...sensorInfo, [name]: value });
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
              Add new sensor
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
                    autoComplete="description"
                    name="description"
                    required
                    fullWidth
                    id="description"
                    label="Description"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    onChange={handleOnChange}
                    required
                    fullWidth
                    id="maxValue"
                    label="Maximum Value"
                    name="maxValue"
                    autoComplete="maxValue"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    onChange={handleOnChange}
                    required
                    fullWidth
                    name="deviceId"
                    label="Assign to device with id"
                    id="deviceId"
                    autoComplete="deviceId"
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Add new sensor
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
