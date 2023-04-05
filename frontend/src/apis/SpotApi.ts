import axios, { AxiosResponse } from "axios";

const SpotApi = () => {
  const doGetSpot = async (
    baseDateDay: string,
    baseDateMonth: string,
    baseDateYear: string
  ) => {
    try {
      const response = await axios.get(
        `${process.env.REACT_APP_SERVER_URI}/grade/?baseDateDay=${baseDateDay}&baseDateHour=00&baseDateMinute=00&baseDateMonth=${baseDateMonth}&baseDateYear=${baseDateYear}&limit=20&searchType=NAME`
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
