"use client";

import styles from "./home.module.scss";

import {SideBar} from "@/app/components/sidebar/sidebar";
import dynamic from "next/dynamic";

import {
  HashRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import {Path} from "@/app/constants";
import {useAppConfig} from "@/app/store/config";


const Chat = dynamic(async () => (await import("@/app/pages/chat/chat")).Chat);
const Role = dynamic(async () => (await import("@/app/pages/role/role")).Role);

function Screen() {
  const config = useAppConfig();
  return (
    <div className={`${config.tightBorder ? styles["tight-container"] : styles.container}`}>
      {/* 工具菜单 */}
      <SideBar/>

      {/* 路由地址 */}
      <div className={styles["window-content"]}>
        <Routes>
          <Route path={Path.Home} element={<Chat/>}/>
          <Route path={Path.Chat} element={<Chat/>}/>
          <Route path={Path.Role} element={<Role/>}/>
        </Routes>
      </div>
    </div>
  );
}

export function Home() {
  return (
    <Router>
      <Screen/>
    </Router>
  );
}
