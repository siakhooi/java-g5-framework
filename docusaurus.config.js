/** @type {import('@docusaurus/types').DocusaurusConfig} */
(module.exports = {
  title: 'G5 Java Application Framework',
  tagline: 'Framework to develop Java desktop applications',
  url: 'https://siakhooi.github.io/',
  baseUrl: '/java-g5-framework/',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  favicon: 'img/favicon.ico',
  organizationName: 'siakhooi',
  projectName: 'java-g5-framework',
  presets: [
    [
      '@docusaurus/preset-classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          routeBasePath: '/',
          editUrl: 'https://github.com/siakhooi/java-g5-framework/edit/master/'
        }
      }),
    ],
  ],
  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      hideableSidebar: true,
      colorMode: {
        defaultMode: 'light',
      },
      announcementBar: {
        id: 'g5-abandoned',
        content:
          'G5 Java Application Framework has been abandoned since 2010, use with care.',
        backgroundColor: '#ff9999',
        textColor: '#000000',
        isCloseable: true,
      },
      navbar: {
        title: 'G5',
        logo: {
          alt: 'G5',
          src: 'img/logo.jpg',
        },
        items: [
          {
            type: 'doc',
            docId: 'prototypes',
            position: 'left',
            label: 'Prototypes',
          },
        ],
      },
      footer: {
        style: 'dark',
      },
    }),
});
