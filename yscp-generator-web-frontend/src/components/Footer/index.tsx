import { GithubOutlined, HeartOutlined, QqOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = 'Yovvis';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'blog',
          title: (
            <>
              <HeartOutlined />
            </>
          ),
          href: 'https://blog.yovvis.top',
          blankTarget: true,
        },
        {
          key: 'github',
          title: (
            <>
              <GithubOutlined />
            </>
          ),
          href: 'https://github.com/yovvis',
          blankTarget: true,
        },
        {
          key: 'qq',
          title: (
            <>
              <QqOutlined />
            </>
          ),
          href: 'https://wpa.qq.com/msgrd?v=3&uin=954061133&site=qq&menu=yes&jumpflag=1',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
