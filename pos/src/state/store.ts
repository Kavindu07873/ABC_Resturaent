import {configureStore} from "@reduxjs/toolkit";
import CalculatorManager from './calc/calculatorManager.ts'

export const store = configureStore({
    reducer:{
        calculator:CalculatorManager
    }
});

export  type  RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof  store.dispatch;