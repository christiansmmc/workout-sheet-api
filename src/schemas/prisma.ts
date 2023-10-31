import { Static, Type } from "@sinclair/typebox";

export const CreateUserSchema = Type.Object({
    email: Type.String({ format: "email" }),
    password: Type.String(),
    client: Type.Object({
        create: Type.Object({
            name: Type.String(),
            weight: Type.String(),
            height: Type.String(),
            clientHistory: Type.Object({
                create: Type.Object({
                    weight: Type.String(),
                    height: Type.String(),
                }),
            }),
        }),
    }),
});

export type CreateUserType = Static<typeof CreateUserSchema>;
