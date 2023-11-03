import { Static, Type } from "@sinclair/typebox";

export const CreateClientRecordSchema = Type.Object({
    weight: Type.Any(),
    height: Type.Any(),
    clientId: Type.String(),
});

export type CreateClientRecordType = Static<typeof CreateClientRecordSchema>;
