import axios, { AxiosResponse } from "axios";

const SpotApi = () => {
  const doGetSpot = async () => {
    try {
      const response = await axios.get(
        `${process.env.REACT_APP_SERVER_URI}/spot`
      );
      return response.data;
    } catch (error: any) {
      return error.response.data;
    }
  };

  return {
    doGetSpot,
  };
};

export default SpotApi;
