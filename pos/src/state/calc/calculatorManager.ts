import {createSlice} from "@reduxjs/toolkit";
// import firebase from "firebase/compat";
// import increment = firebase.database.ServerValue.increment;

interface ManagerCalcState{
    value:number;
}

const initialState:ManagerCalcState={
    value:0
}

const manageSlice = createSlice({
    name:'calculator',
    initialState,
    reducers:{
        increment:(state)=>{
            state.value +=1;
        },
        decrement:(state)=>{
            state.value -=1;
        },
    }
});

export const {increment , decrement} = manageSlice.actions;
export default manageSlice.reducer;