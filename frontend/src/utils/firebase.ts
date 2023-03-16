// Import the functions you need from the SDKs you need
import { initializeApp } from 'firebase/app';

import { getStorage } from 'firebase/storage';

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyB4WCdJnbnx-qFXuOnobgbibZhpjx-qAo0",
  authDomain: "counting-star-e6435.firebaseapp.com",
  projectId: "counting-star-e6435",
  storageBucket: "counting-star-e6435.appspot.com",
  messagingSenderId: "474995817444",
  appId: "1:474995817444:web:01869a981ea646b23ba5f1"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);

export default app;