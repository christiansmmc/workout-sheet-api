import prisma from "../config/prisma";
import { UpdateClientType } from "../schemas/client";

export const findByUserId = async (userId: string) => {
    const client = await prisma.client.findUnique({
        where: { user_id: userId },
        include: { clientHistory: true },
    });

    if (!client) {
        throw new Error("User not found");
    }

    return client;
};

export const update = async (id: string, data: UpdateClientType) =>
    await prisma.client.update({
        where: {
            id,
        },
        data,
    });
