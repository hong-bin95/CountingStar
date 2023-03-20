import {GoogleMap, LoadScript, MarkerF} from '@react-google-maps/api'
import {useMemo} from 'react'
import styled from 'styled-components'

function GoogleMain() {
  const center = useMemo(() => ({lat: 37.541, lng: 126.986}), [])

  return (
    <Wrapper>
      <LoadScript googleMapsApiKey={`${process.env.REACT_APP_GOOGLE_MAP_KEY}`}>
        <GoogleMap zoom={13} center={center} mapContainerClassName="map-container">
          <MarkerF position={center} icon={{url: '/images/icons/map_marker.svg', scale: 5}} />
        </GoogleMap>
      </LoadScript>
    </Wrapper>
  )
}
export default GoogleMain

const Wrapper = styled.div`
  .map-container {
    width: 100vw;
    height: 100vh;
  }
`