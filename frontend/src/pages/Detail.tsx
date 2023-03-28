import React, {useState, useEffect} from 'react';
import DetailsMain from '../components/DetailPage/DetailsMain';
import { useParams } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { updateSpotId } from '../store/DetailsSlice';
import axios from 'axios';

function Detail() {
  const dispatch = useDispatch();

  const { spotId } = useParams<{ spotId: string | undefined }>();
  const [spotIdNumber, setSpotIdNumber] = useState<number>(0);
  
  useEffect(() => {
    if (spotId) {
      setSpotIdNumber(parseInt(spotId, 10));
      dispatch(updateSpotId(spotIdNumber));
    }
  }, [spotId]);

  useEffect(() => {
    axios
      .get(`https://counting-star.com/api/spot/${spotIdNumber}`,{
        params:{
          spotId:String(spotIdNumber),
        },
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  },[]);

  return (
    <div>
      <DetailsMain />
    </div>
  );
}

export default Detail;
