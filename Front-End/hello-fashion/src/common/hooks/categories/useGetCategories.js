import {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {getAllCategories} from "../../../features/categories/categoriesServices";
import {resetMutationResult} from "../../../features/categories/categoriesSlice";

const useGetCategories = () => {
  const dispatch = useDispatch();
  const {allCategories, isMutation} = useSelector((state) => state.categories);

  useEffect(() => {
    if (isMutation.success) {
      dispatch(getAllCategories()); // Call without pagination parameters
      dispatch(resetMutationResult());
    } else {
      dispatch(getAllCategories()); // Call without pagination parameters
    }
  }, [dispatch, isMutation.success]);

  return {allCategories, isMutation};
};

export default useGetCategories;
