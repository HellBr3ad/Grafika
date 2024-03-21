import pygame
import math
import time

pygame.init()
win = pygame.display.set_mode((600, 600))
pygame.display.set_caption("trapezoid")

# deklarowanie kolorów
CZERWONY = (255, 0, 0)
ZIELONY = (0, 255, 0)
ZOLTY = (255, 255, 0)
FIOLETOWY = (128, 0, 128)
JASNY_NIEBIESKI = (0, 255, 255)
POMARANCZOWY = (255, 165, 0)
NIEBIESKI = (0, 0, 255)
SZARY = (128, 128, 128)

trackable_keys = [pygame.K_1, pygame.K_2, pygame.K_3, pygame.K_4, pygame.K_5, pygame.K_6, pygame.K_7, pygame.K_8, pygame.K_9]

toggled = {key: {'pressed' : False, 'at' : None} for key in trackable_keys}

run = True
while run:

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            run = False

    win.fill("white")
    pygame.draw.rect(win, ZOLTY , (10, 45, 580, 545))
    font = pygame.font.SysFont('bitstreamverasans', 30)
    trapezoid_surface = pygame.Surface((400, 400), pygame.SRCALPHA)

    def draw_trapezoid(surface, color, center, size):
        top_width = size * 0.6
        bottom_width = size
        height = size * 0.5

        cx, cy = center
        top_left = (cx - top_width / 2, cy - height / 2)
        top_right = (cx + top_width / 2, cy - height / 2)
        bottom_left = (cx - bottom_width / 2, cy + height / 2)
        bottom_right = (cx + bottom_width / 2, cy + height / 2)

        pygame.draw.polygon(surface, color, [top_left, top_right, bottom_right, bottom_left], 0)


    def shear_trapezoid(surface, color, center, size, shear_amount_x, shear_amount_y):
        top_width = size * 0.6
        bottom_width = size
        height = size * 0.5

        cx, cy = center
        top_left = (cx - top_width / 2, cy - height / 2)
        top_right = (cx + top_width / 2, cy - height / 2)
        bottom_left = (cx - bottom_width / 2, cy + height / 2)
        bottom_right = (cx + bottom_width / 2, cy + height / 2)

        top_left_sheared = (top_left[0] + shear_amount_x * (top_left[1] - cy) / height, top_left[1] + shear_amount_y * (top_left[0] - cx) / top_width)
        top_right_sheared = (top_right[0] + shear_amount_x * (top_right[1] - cy) / height, top_right[1] + shear_amount_y * (top_right[0] - cx) / top_width)
        bottom_left_sheared = (bottom_left[0] + shear_amount_x * (bottom_left[1] - cy) / height, bottom_left[1] + shear_amount_y * (bottom_left[0] - cx) / bottom_width)
        bottom_right_sheared = (bottom_right[0] + shear_amount_x * (bottom_right[1] - cy) / height, bottom_right[1] + shear_amount_y * (bottom_right[0] - cx) / bottom_width)

        pygame.draw.polygon(surface, color, [top_left_sheared, top_right_sheared, bottom_right_sheared, bottom_left_sheared], 0)

    keys = pygame.key.get_pressed()
    for key in trackable_keys:
        if not keys[key]:
            continue
        at = toggled[key]['at']
        if at is None or time.time() - at > 0.25:
            toggled[key]['at'] = time.time()
            toggled[key]['pressed'] = not toggled[key]['pressed']  
    
    if  toggled[pygame.K_1]['pressed']:
        label = font.render('1.', 1, (0, 0, 0))
        win.blit(label, (10, 10))
        draw_trapezoid(win,NIEBIESKI, (300, 300), 100)
    
    if toggled[pygame.K_2]['pressed']:
        label = font.render('2.', 1, (0, 0, 0))
        win.blit(label, (10, 10))

        draw_trapezoid(trapezoid_surface, NIEBIESKI, (200, 200), 100) 

        rotated_trapezoid = pygame.transform.rotozoom(trapezoid_surface, 45, 2)
        rect = rotated_trapezoid.get_rect(center=(300, 300))
        win.blit(rotated_trapezoid, rect)

    if toggled[pygame.K_3]['pressed']:

        label = font.render('3.', 1, (0, 0, 0))
        win.blit(label, (10, 10)) 

        draw_trapezoid(trapezoid_surface, NIEBIESKI, (200, 200), 100)

        flipped_trapezoid = pygame.transform.flip(trapezoid_surface, flip_x=1, flip_y=0)
        rect = flipped_trapezoid.get_rect(center=(300, 300))
       
        scaled_trapezoid = pygame.transform.scale_by(flipped_trapezoid, (0.5, 2))
        rect = scaled_trapezoid.get_rect(center=(300, 300))
        win.blit(scaled_trapezoid, rect)

    if toggled[pygame.K_4]['pressed']:
        label = font.render('4.', 1, (0, 0, 0))
        win.blit(label, (10, 10))

        shear_amount_x = 20.0
        shear_amount_y = 20.0
        shear_trapezoid(win, NIEBIESKI, (300, 300), 100, shear_amount_x, shear_amount_y)
    
    if toggled[pygame.K_5]['pressed']:
        label = font.render('5.', 1, (0, 0, 0))
        win.blit(label, (10, 10))
        draw_trapezoid(trapezoid_surface, NIEBIESKI, (200, 200), 100)
        scaled_trapezoid = pygame.transform.scale_by(trapezoid_surface, (2, 0.5))
        rect = scaled_trapezoid.get_rect(center=(300, 100))
        win.blit(scaled_trapezoid, rect)

    if toggled[pygame.K_6]['pressed']:
        label = font.render('6.', 1, (0, 0, 0))
        win.blit(label, (10, 10))

        shear_amount_x = 20.0
        shear_amount_y = 20.0
        shear_trapezoid(trapezoid_surface, NIEBIESKI, (300, 300), 100, shear_amount_x, shear_amount_y)

        rotated_trapezoid2 = pygame.transform.rotate(trapezoid_surface, 90)
        rect = rotated_trapezoid2.get_rect(center=(200, 400))
        win.blit(rotated_trapezoid2, rect)
    
    if toggled[pygame.K_7]['pressed']:
        label = font.render('7.', 1, (0, 0, 0))
        win.blit(label, (10, 10))

        draw_trapezoid(trapezoid_surface, NIEBIESKI, (200, 200), 100)

        flipped_trapezoid = pygame.transform.flip(trapezoid_surface, 0, 1)
        rect = flipped_trapezoid.get_rect(center=(300, 300))
       
        scaled_trapezoid = pygame.transform.scale_by(flipped_trapezoid, (0.5, 2))
        rect = scaled_trapezoid.get_rect(center=(300, 300))
        win.blit(scaled_trapezoid, rect)
    
    if toggled[pygame.K_8]['pressed']:
        label = font.render('8.', 1, (0, 0, 0))
        win.blit(label, (10, 10))

        draw_trapezoid(trapezoid_surface, NIEBIESKI, (200, 200), 100)

        scaled_trapezoid = pygame.transform.scale_by(trapezoid_surface, (2, 0.5))
        rect = scaled_trapezoid.get_rect(center=(300, 100))
        
        rotated_trapezoid = pygame.transform.rotate(scaled_trapezoid, -45)
        rect = rotated_trapezoid.get_rect(center=(300, 400))
        win.blit(rotated_trapezoid, rect) 

    if toggled[pygame.K_9]['pressed']:
        label = font.render('9.', 1, (0, 0, 0))
        win.blit(label, (10, 10))

        shear_amount_x = 20.0
        shear_amount_y = 20.0
        shear_trapezoid(trapezoid_surface, NIEBIESKI, (300, 300), 100, shear_amount_x, shear_amount_y)
        
        rotated_trapezoid = pygame.transform.rotate(trapezoid_surface, -30)
        rect = rotated_trapezoid.get_rect(center=(470, 100))
        win.blit(rotated_trapezoid, rect) 

    pygame.display.update()

