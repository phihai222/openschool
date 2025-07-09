import React from 'react';
import {HeaderParent} from "@shared/components/layout/Header/index.jsx";
import Body from "@shared/components/layout/Body/index.jsx";
import usePWADetection from "@shared/hooks/pwa/usePWADetection.jsx";
import Button from "@shared/components/ui/Button/index.js";
import {useAuthStore} from "@shared/store/useAuthStore/index.js";

const Setting = () => {
  const {status} = usePWADetection();

  const logout = useAuthStore((state) => state.logout)
  return (
    <div>
      <HeaderParent ScreenTitle={"Setting"}/>
      <Body>


        <div style={{marginBottom: 20}}>
          {status.isIOS && status.isIOS ? (
            <div>PWA ios</div>
          ) : <h3>This device is not iOS</h3>}
        </div>
        <Button onClick={logout} label={"Logout"}/>
      </Body>
    </div>
  );
};

export default Setting;