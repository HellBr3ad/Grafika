import pygame

pygame.init()

GREEN = (0, 255, 0)
WHITE = (255, 255, 255)

screen_width = 300
screen_height = 300
border_width = 20

new_screen_width = screen_width - 2 * border_width
new_screen_height = screen_height - 2 * border_width

screen = pygame.display.set_mode((screen_width, screen_height))

pygame.display.set_caption('Green Square with Centered Upside Down White Triangle')

running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    screen.fill(WHITE)

    green_rect = pygame.Rect(border_width, border_width, new_screen_width, new_screen_height)
    pygame.draw.rect(screen, GREEN, green_rect)

    top_point_y = border_width + new_screen_height // 2
    triangle_points = [
        (border_width, new_screen_height + border_width),
        (screen_width // 2, top_point_y),
        (new_screen_width + border_width, new_screen_height + border_width)
    ]
    pygame.draw.polygon(screen, WHITE, triangle_points)

    pygame.display.flip()

pygame.quit()
