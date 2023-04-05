import React from 'react';
import { useSelector } from 'react-redux';
import { DetailsData } from '../../types/DetailsType';

function PlaceTitle() {
    const spotName = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.spotName);
    const spotId = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.spotId);

    return (
        <div className="mt-5 mr-10">
            {spotName?spotName:spotId}
        </div>
    );
}

export default PlaceTitle;