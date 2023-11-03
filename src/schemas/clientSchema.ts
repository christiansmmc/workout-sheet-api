import { Static, Type } from "@sinclair/typebox";

export const UpdateClientSchema = Type.Object({
    name: Type.String(),
    weight: Type.Any(),
    height: Type.Any(),
});

export const UpdateClientResponseSchema = Type.Object({
    id: Type.String(),
    name: Type.String(),
    weight: Type.Any(),
    height: Type.Any(),
});

export type UpdateClientType = Static<typeof UpdateClientSchema>;
export type UpdateClientResponseType = Static<
    typeof UpdateClientResponseSchema
>;
