import React, {useState, useEffect, useRef} from 'react';
import styled from 'styled-components';
import logo from '../logo.svg';

function ContainerButton() {
    
    const [now, setNow] = useState<number>(1);
    const moveUp = () => setNow(now - 1);
    const moveDown = () => setNow(now + 1);

    // const btnUp = useRef<HTMLImageElement>(null);
    // const btnDown = useRef<HTMLImageElement>(null);
    const btnUp = useRef() as React.MutableRefObject<HTMLImageElement>;
    const btnDown = useRef() as React.MutableRefObject<HTMLImageElement>;

    useEffect(() => {
        if(now === 1) {
            btnDown.current.style.display = "none";
            btnUp.current.style.display = "inline";

        }
        else{
            btnDown.current.style.display = "inline";
            btnUp.current.style.display = "none";
        }
    }, [now]);

    return (
        <div>
            <img src='../logo.svg' alt="up" onClick={moveUp} ref={btnUp}/>
            <img src="./박찬호.jpg" alt="do" onClick={moveDown} ref={btnDown}/>
        </div>
    );
}

export default ContainerButton;