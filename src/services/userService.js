import prisma from "./prisma.js";

export async function createUser(data) {
    return prisma.user.create({ data, include: { client: true } });
}

export async function findUserByEmail(email) {
    return prisma.user.findUnique({ where: { email } });
}
