import axios from "axios";

// Server URL
const axiosBaseUrl = axios.create({baseURL: "http://localhost:8080/"});

export default axiosBaseUrl;
