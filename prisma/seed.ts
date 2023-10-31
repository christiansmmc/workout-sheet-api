import prisma from "../src/config/prisma";
import { exercises } from "../src/data/exercises";

async function main() {
    await prisma.exercise.createMany({
        data: exercises,
    });
}

main()
    .then(async () => {
        await prisma.$disconnect();
    })
    .catch(async (e) => {
        console.error(e);
        await prisma.$disconnect();
        process.exit(1);
    });
