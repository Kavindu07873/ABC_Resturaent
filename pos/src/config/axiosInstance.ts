import axios ,{AxiosInstance} from "axios";
import BASE_URL from "../config/ApiConfig.ts";

const instance:AxiosInstance = axios.create({
    baseURL:BASE_URL
    });

instance.interceptors.request.use(
    (config)=>{
        console.log('interceptor');


        let token = document.cookie.split('; ')
            .find(record => record.startsWith('token=')) || null;
        // @ts-ignore
        token = token?.split('=')[1];
        config.headers.Authorization=token;
        return config;
    },
    (error)=>{return Promise.reject(error)}
);

export default instance;