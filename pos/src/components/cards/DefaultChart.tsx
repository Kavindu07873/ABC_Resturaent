// import React from "react";
import { Line } from 'react-chartjs-2';
// me dekama hari import karama
// import {
//     Chart as ChartJS,
//     CategoryScale,
//     LinearScale,
//     PointElement,
//     LineElement,
//     Title,
//     Tooltip,
//     Legend
// } from 'chart.js';
//
// ChartJS.register(
//     CategoryScale,
//     LinearScale,
//     PointElement,
//     LineElement,
//     Title,
//     Tooltip,
//     Legend
// );

// mekath hari
import {defaults} from "chart.js/auto";
defaults.responsive = true;
defaults.plugins.title.display = true;
defaults.plugins.title.align ="start";
defaults.plugins.title.color ="black";

function DefaultChart() {

    const data={
        labels:['January','February','March','April','May','June','July','August','September','October','November','December'],
        datasets:[{
            label:'data',
            data:[50,23,45,79,45,89,76,34,67,12,45,68],
            fill:false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }]
    }

    const options ={
        scales:{
            y:{
                beginAtZero:true
            }
        }
    }

    return (
        <>
               <Line
                   options={options}
                   data={data}
               />
        </>
    )
}

export default DefaultChart;
