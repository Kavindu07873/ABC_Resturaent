import firebase from "firebase/compat/app";
import 'firebase/compat/auth'
import 'firebase/compat/firestore'
import 'firebase/compat/storage'

// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyD0PK6b4tJYN6mX8jPWoLI2dFY6HIVvHN0",
    authDomain: "react-pos-78855.firebaseapp.com",
    projectId: "react-pos-78855",
    storageBucket: "react-pos-78855.appspot.com",
    messagingSenderId: "884833985263",
    appId: "1:884833985263:web:e454bbcfe5660e1a21f11a",
    measurementId: "G-MLFZZ18SCN"
};
// const firebaseConfig = {
//     apiKey: "AIzaSyD0PK6b4tJYN6mX8jPWoLI2dFY6HIVvHN0",
//     authDomain: "react-pos-78855.firebaseapp.com",
//     projectId: "react-pos-78855",
//     storageBucket: "react-pos-78855.appspot.com",
//     messagingSenderId: "884833985263",
//     appId: "1:884833985263:web:e454bbcfe5660e1a21f11a",
//     measurementId: "G-MLFZZ18SCN"
// };
// const firebaseConfig = {
//     apiKey: "AIzaSyAN3xBj5b8MZhMlHp5MdDybXH4j-aYwNJc",
//     authDomain: "mern-course-work.firebaseapp.com",
//     projectId: "mern-course-work",
//     storageBucket: "mern-course-work.appspot.com",
//     messagingSenderId: "712309837851",
//     appId: "1:712309837851:web:af050caf1b5d3c032b28be",
//     measurementId: "G-ZLGJV3BZ3R"
// };
// Initialize Firebase
if (!firebase.apps.length) {
    firebase.initializeApp(firebaseConfig);
}

export const storage = firebase.storage();
export default firebase;