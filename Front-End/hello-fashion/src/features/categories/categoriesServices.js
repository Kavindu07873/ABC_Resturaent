import {createAsyncThunk} from "@reduxjs/toolkit";
import pushNotification from "../../common/components/Shared/Notification";
import {useDeleteData} from "../../common/hooks/api/useDeleteData";
import {useGetData} from "../../common/hooks/api/useGetData";
import {usePostDataWithImg} from "../../common/hooks/api/usePostData";
import {useUpdateDataWithImg} from "../../common/hooks/api/useUpdateData";

//_____________________GET_ALL_CATEGORIES____________________//
// export const getAllCategories = createAsyncThunk(
//   "categories/getAllCategories",
//   async ({limit, page}, {rejectWithValue}) => {
//     try {
//       const res = await useGetData(
//         `/categories${limit ? `?limit=${limit}` : ""}${
//           page ? `&page=${page}` : ""
//         }`
//       );
//       return res;
//     } catch (error) {
//       // console.log(error);
//       const message =
//         (error.response &&
//           error.response.data &&
//           error.response.data.message) ||
//         (error.response && error.response.data && error.response.data.errors) ||
//         error.message;
//       // console.log(message);
//       return rejectWithValue(message);
//     }
//   }
// );

export const getAllCategories = createAsyncThunk(
  "categories/getAllCategories",
  async ({ size = 10, page = 0 }, { rejectWithValue }) => {
    try {
      // Construct query string for pagination
      const queryParams = new URLSearchParams();
      queryParams.append("size", size); // 'size' is equivalent to 'limit'
      queryParams.append("page", page);

      // Send request with query params
      const res = await useGetData(`/categories?${queryParams.toString()}`);

      return res;
    } catch (error) {
      const message =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        (error.response && error.response.data && error.response.data.errors) ||
        error.message;
      return rejectWithValue(message);
    }
  }
);


//_____________________GET_CATEGORY_DETAILS____________________//
export const getCategoryDetails = createAsyncThunk(
  "categories/getCategoryDetails",
  async (categoryId, {rejectWithValue}) => {
    try {
      const res = await useGetData(`/categories/${categoryId}`);
      return res;
    } catch (error) {
      // console.log(error);
      const message =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        (error.response && error.response.data && error.response.data.errors) ||
        error.message;
      // console.log(message);
      return rejectWithValue(message);
    }
  }
);
//_____________________CREATE_CATEGORY____________________//
export const createCategory = createAsyncThunk(
  "categories/createCategory",
  async (body, {rejectWithValue}) => {
    try {
      const res = await usePostDataWithImg(`/categories`, body);
      pushNotification("Category Created Successfully", "success");
      // console.log(res);
      return res;
    } catch (error) {
      // console.log("ERROR" + error);
      const message =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        (error.response && error.response.data && error.response.data.errors) ||
        error.message;
      // console.log(message);
      if (typeof message === "string") {
        pushNotification(message, "error");
      } else {
        message.forEach((el) => {
          pushNotification(el.msg, "error");
        });
      }
      return rejectWithValue(message);
    }
  }
);
//_____________________UPDATE_CATEGORY____________________//
export const updateCategory = createAsyncThunk(
  "categories/updateCategory",
  async ({categoryId, body}, {rejectWithValue}) => {
    try {
      const res = await useUpdateDataWithImg(`/categories/${categoryId}`, body);
      pushNotification("Category Updated Successfully", "success");
      // console.log(res);
      return res;
    } catch (error) {
      // console.log("ERROR" + error);
      const message =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        (error.response && error.response.data && error.response.data.errors) ||
        error.message;
      // console.log(message);
      if (typeof message === "string") {
        pushNotification(message, "error");
      } else {
        message.forEach((el) => {
          pushNotification(el.msg, "error");
        });
      }
      return rejectWithValue(message);
    }
  }
);
//_____________________DELETE_CATEGORY____________________//
export const deleteCategory = createAsyncThunk(
  "categories/deleteCategory",
  async (categoryId, {rejectWithValue}) => {
    try {
      const res = await useDeleteData(`/categories/${categoryId}`);
      pushNotification("Category Deleted Successfully", "success");
      // console.log(res);
      return res;
    } catch (error) {
      // console.log("ERROR" + error);
      const message =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        (error.response && error.response.data && error.response.data.errors) ||
        error.message;
      // console.log(message);
      if (typeof message === "string") {
        pushNotification(message, "error");
      } else {
        message.forEach((el) => {
          pushNotification(el.msg, "error");
        });
      }
      return rejectWithValue(message);
    }
  }
);
