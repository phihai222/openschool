/*
* This function take path to component and render on description of storybook
* Add more params if you need.
* */

export const defineComponentPath = (path: string, layout = 'centered') => {
    return {
        layout: layout,
        docs: {
            description: {
                component: `📄 \`${path}\``,
            },
        },
    };
};

