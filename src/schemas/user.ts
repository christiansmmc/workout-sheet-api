import { Static, Type } from "@sinclair/typebox";

export const LoginSchema = Type.Object({
    email: Type.String({ format: "email" }),
    password: Type.String(),
});

export const LoginResponseSchema = Type.Object({
    access_token: Type.String(),
});

export const CreateAccountSchema = Type.Object({
    email: Type.String({ format: "email" }),
    password: Type.String(),
    client: Type.Object({
        name: Type.String(),
        weight: Type.String(),
        height: Type.String(),
    }),
});

export const CreateAccountResponseSchema = Type.Object({
    id: Type.Optional(Type.String()),
    email: Type.String({ format: "email" }),
    client: Type.Object({
        id: Type.Optional(Type.String()),
        name: Type.String(),
        weight: Type.String(),
        height: Type.String(),
    }),
});

export const GetUserResponseSchema = Type.Object({
    id: Type.String(),
    email: Type.String(),
    client: Type.Object({
        id: Type.String(),
        name: Type.String(),
        weight: Type.String(),
        height: Type.String(),
    }),
});

export type LoginType = Static<typeof LoginSchema>;
export type CreateAccountType = Static<typeof CreateAccountSchema>;
