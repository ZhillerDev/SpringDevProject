"use client";

import styles from "./home.module.scss";

import {SideBar} from "@/app/components/sidebar";
import dynamic from "next/dynamic";

import {
  HashRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import {Path} from "@/app/constants";


const Chat = dynamic(async () => (await import("./chat/chat")).Chat);
const Role = dynamic(async () => (await import("./role/role")).Role);

function Screen() {
  return (
    <div className={styles.container}>
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
