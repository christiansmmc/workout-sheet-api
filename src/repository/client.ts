import prisma from "../config/prisma";
import { UpdateClientType } from "../schemas/client";

export const findByUserId = async (userId: string) =>
    await prisma.client.findUnique({
        where: { user_id: userId },
        include: { clientHistory: true },
    });

export const update = async (id: string, data: UpdateClientType) =>
    await prisma.client.update({
        where: {
            id,
        },
        data,
    });
