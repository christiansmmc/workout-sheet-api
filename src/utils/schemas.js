export const createAccountBodySchema = {
    type: "object",
    properties: {
        email: { type: "string" },
        password: { type: "string" },
        client: {
            type: "object",
            properties: {
                name: { type: "string" },
                weight: { type: "number" },
                height: { type: "number" },
            },
            required: ["name", "weight", "height"],
        },
    },
    required: ["email", "password"],
};

export const createAccountResponseSchema = {
    type: "object",
    properties: {
        id: { type: "string" },
        email: { type: "string" },
        client: {
            type: "object",
            properties: {
                id: { type: "string" },
                name: { type: "string" },
                weight: { type: "number" },
                height: { type: "number" },
            },
        },
    },
};

export const loginBodySchema = {
    type: "object",
    properties: {
        email: { type: "string" },
        password: { type: "string" },
    },
    required: ["email", "password"],
};

export const loginResponseSchema = {
    type: "object",
    properties: {
        access_token: { type: "string" },
    },
};
