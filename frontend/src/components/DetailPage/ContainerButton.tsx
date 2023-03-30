import React, {useState, useEffect, useRef} from 'react';
import UpBtn from '../../assets/UpBtn.png';
import DownBtn from '../../assets/DownBtn.png';

type ButtonProps = {
    onClick: () => void;
  };

function ContainerButton({ onClick }: ButtonProps) {
    
    const [now, setNow] = useState<number>(1);

    const btnUp = useRef() as React.MutableRefObject<HTMLImageElement>;
    const btnDown = useRef() as React.MutableRefObject<HTMLImageElement>;

    const styled = {
        height : '25px',
        width : '25px',
    }

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

    const moveUp = () => {
        onClick();
        setNow(now - 1);
    }

    const moveDown = () => {
        onClick();
        setNow(now + 1);
    }

    return (
        <div className="mr-1 mt-6">
            <img style={styled} src={UpBtn} alt="up" onClick={moveUp} ref={btnUp}/>
            <img style={styled} src={DownBtn} alt="do" onClick={moveDown} ref={btnDown}/>
        </div>
    );
}

export default ContainerButton;