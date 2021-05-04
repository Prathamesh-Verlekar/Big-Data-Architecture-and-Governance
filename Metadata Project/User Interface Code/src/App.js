import React, { useState } from 'react';
import './App.css';
import 'antd/dist/antd.css';
import { Layout, Menu } from 'antd';
import DBS from './Components/DBS';

const { Header, Content, Footer } = Layout;

function App() {
  
  const [navId, setNavId] = useState('1');

  const changeNavId = value => {
    setNavId(value);
  }

  return (
    <div className="App">
      <Layout className="layout">
        <Header>
          <img className="logo" src='/logo.png' alt='' />
          <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']}>
            <Menu.Item key="1" onClick={() => {changeNavId('1')}}>OUR DATABASES</Menu.Item>
            <Menu.Item key="2" onClick={() => {changeNavId('2')}}>ABOUT US</Menu.Item>
          </Menu>
        </Header>
        <Content style={{ padding: '0 50px' }}>
          <div style={{ margin: '16px 0' }}>
            {navId === '1' ? 'Search Teams Databases Infomation' : 'This Is What About Us'}
          </div>
          <div className="site-layout-content">
            {navId === '1' ? <DBS /> : <div>about us</div>}
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}>Web Application ©2021 Created by NEU</Footer>
      </Layout>
    </div>
  );
}

export default App;
