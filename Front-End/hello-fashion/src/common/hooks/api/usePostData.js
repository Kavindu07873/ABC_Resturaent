import axiosBaseUrl from "../../../app/axiosBaseUrl";
import axios from 'axios';

export const usePostData = async (url, data) => {
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };
  const res = await axiosBaseUrl.post(url, data, config);
  return res;
};
export const usePostDataWithImg = async (url, data) => {
  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };
  const res = await axiosBaseUrl.post(url, data, config);
  return res;
};

export const usePostDataWithoutAuth = async (url, data) => {
  const response = await axiosBaseUrl.post(url, data);
  return response;
};

export const usePostDataWithBasicAuth = async (url, data) => {
  const clientId = "ceyentra";
  const clientSecret = "ceyentra";

  // Encode credentials and create the Authorization header
  const authHeader = `Basic ${encodeCredentials(clientId, clientSecret)}`;
  console.log(`Encoded credentials: ${encodeCredentials(clientId, clientSecret)}`);

  const response = await axiosBaseUrl.post(url, data, {
    headers: {
      Authorization: authHeader,
      "Content-Type": "application/x-www-form-urlencoded",
    },
  });
  return response;
};

// Function to encode credentials using btoa
const encodeCredentials = (clientId, clientSecret) => {
  const credentials = `${clientId}:${clientSecret}`;
  return btoa(credentials); // Use btoa for base64 encoding in the browser
};
