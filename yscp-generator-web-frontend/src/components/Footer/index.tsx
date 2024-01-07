import { createFromIconfontCN, GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import Space from 'antd/es/space';
import React from 'react';

const IconFont = createFromIconfontCN({
  scriptUrl: [
    '//at.alicdn.com/t/c/font_4391036_568uf15zcax.js', // icon-javascript, icon-java, icon-shoppingcart (overridden)
  ],
});

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
              <Space>
                <IconFont type="icon-i8home-" />
              </Space>{' '}
              博客
            </>
          ),
          href: 'https://blog.yovvis.top',
          blankTarget: true,
        },
        {
          key: 'github',
          title: (
            <>
              <GithubOutlined /> GitHub
            </>
          ),
          href: 'https://github.com/yovvis',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
