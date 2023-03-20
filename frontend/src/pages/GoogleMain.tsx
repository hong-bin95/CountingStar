import {GoogleMap, LoadScript, MarkerF} from '@react-google-maps/api'
import {useMemo} from 'react'
import styled from 'styled-components'

function MapComponent() {
  const center = useMemo(() => ({lat: 37.541, lng: 126.986}), [])

  return (
    <Wrapper>
      <LoadScript googleMapsApiKey="AIzaSyCNuKH6J4n6OgN_d4Rzga8jujPXfx3yOWM">
        <GoogleMap zoom={18} center={center} mapContainerClassName="map-container">
          <MarkerF position={center} icon={{url: '/images/icons/map_marker.svg', scale: 5}} />
        </GoogleMap>
      </LoadScript>
    </Wrapper>
  )
}
export default MapComponent

const Wrapper = styled.div`
  .map-container {
    width: 100vw;
    height: 100vh;
  }
`