import React from 'react';
import { useSelector } from 'react-redux';
import {DetailsData} from '../../store/DetailsSlice';
function PlaceTitle() {
    const spotName = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.spotName);
    const spotId = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.spotId);

    return (
        <div className="mt-5 mr-10">
            {spotName?spotName:spotId}
        </div>
    );
}

export default PlaceTitle;